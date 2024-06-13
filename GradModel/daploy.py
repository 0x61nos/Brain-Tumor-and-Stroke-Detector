
from tkinter import Image
import numpy as np
import cv2  # Assuming OpenCV for image processing
import urllib.request

from tensorflow.keras.applications.mobilenet_v2 import preprocess_input
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model

import numpy as np
from flask import Flask, request, jsonify, send_from_directory
import os
from flask import Flask, flash, request, redirect, url_for
from werkzeug.utils import secure_filename

# (Your ML model loading and prediction logic here)
def detect_and_predict_mask(frame, faceNet, maskNet):
	# grab the dimensions of the frame and then construct a blob
	# from it
	(h, w) = frame.shape[:2]
	blob = cv2.dnn.blobFromImage(frame, 1.0, (224, 224),
		(104.0, 177.0, 123.0))

	# pass the blob through the network and obtain the face detections
	faceNet.setInput(blob)
	detections = faceNet.forward()
# 	print(detections.shape)

	# initialize our list of faces, their corresponding locations,
	# and the list of predictions from our face mask network
	faces = []
	locs = []
	preds = []

	# loop over the detections
	for i in range(0, detections.shape[2]):
		# extract the confidence (i.e., probability) associated with
		# the detection
		confidence = detections[0, 0, i, 2]

		# filter out weak detections by ensuring the confidence is
		# greater than the minimum confidence
		if confidence > 0.5:
			# compute the (x, y)-coordinates of the bounding box for
			# the object
			box = detections[0, 0, i, 3:7] * np.array([w, h, w, h])
			(startX, startY, endX, endY) = box.astype("int")

			# ensure the bounding boxes fall within the dimensions of
			# the frame
			(startX, startY) = (max(0, startX), max(0, startY))
			(endX, endY) = (min(w - 1, endX), min(h - 1, endY))

			# extract the face ROI, convert it from BGR to RGB channel
			# ordering, resize it to 224x224, and preprocess it
			face = frame[startY:endY, startX:endX]
			face = cv2.cvtColor(face, cv2.COLOR_BGR2RGB)
			face = cv2.resize(face, (224, 224))
			face = img_to_array(face)
			face = preprocess_input(face)

			# add the face and bounding boxes to their respective
			# lists
			faces.append(face)
			locs.append((startX, startY, endX, endY))

	# only make a predictions if at least one face was detected
	if len(faces) > 0:
		# for faster inference we'll make batch predictions on all
		# faces at the same time rather than one-by-one predictions
		# in the above for loop
		faces = np.array(faces, dtype="float32")
		preds = maskNet.predict(faces, batch_size=32)

	# return a 2-tuple of the face locations and their corresponding
	# locations
	return (locs, preds)



UPLOAD_FOLDER = './upload'
ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])

app = Flask(__name__)
# ---------------------------------------------------------------------
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
# ---------------------------------------------------------------------
def get_file_extension(file_name):
    if file_name is None:
        return None
    file_name_parts = file_name.split('.')
    return file_name_parts[-1]
    
#-----------------------------------------------------------------------
import uuid

def generate_unique_filename():
    return str(uuid.uuid4().hex)
#------------------------------------------------------------------------

# load our serialized face detector model from disk
prototxtPath = r"face_detector\deploy.prototxt"
weightsPath = r"face_detector\res10_300x300_ssd_iter_140000.caffemodel"
# faceNet = cv2.dnn.readNet(prototxtPath, weightsPath)
# ---------------------------------------------------------------------------------------------------------------------
# load the face mask detector model from disk
maskNet = load_model("./my_model.h5")
# -----------------------------------------------------------------------------------------------------------------------
# @app.route('/')
@app.route('/predict', methods=['POST'])
def predict():
    
    # return "Hello"
    # if request.method == 'POST':
	# -------------------------------------------------------------------------------
	# if 'image' not in request.files:
	#     return jsonify({'error':'media required'}),400
	# ------------------------------------------------------------------------------------------
	# Get image data from request
	file=request.files['image']
	textt=request.content_type
	print(textt)
	vll=request.headers
	print(vll)
	strr=get_file_extension(file.filename)
	unique_filename = generate_unique_filename()
	newfileName =unique_filename + "-file." + strr
	file.filename=newfileName
	# -------------------------------------------------------------------------------------------
	filename = secure_filename(file.filename)
	file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
	# ------------------------------------------------------------------------------------------
	image_file = cv2.imread("upload/"+file.filename)
# ------------------------------------هستخدم الجزء دا لو هحمل الصور ف سيرفر الجافا----------------------------------------------------
    # req = urllib.request.urlopen("http://localhost:8080/uploads/files/1712525929264-file.jpg")//
	# arr = np.asarray(bytearray(req.read()), dtype=np.uint8)
	# img = cv2.imdecode(arr, -1) # 'Load it as it is'
	# ----------------------------------------------------------------------------------------
	image_file =  cv2.resize(image_file,(340,340))     # resize image to match model's expected sizing
	# image_file =  cv2.resize(img,(340,340))
	image_file = image_file.reshape(1,340,340,3)
	# ----------------------------------------------------------------------------------------------------
	image_array = np.array(image_file, dtype="float32")
	# # # Make prediction using your ML model
	result=maskNet.predict(image_array, )
	# ----------------------------------------------------------------------------
	# if result:
	print(result[0][0])
	if result[0][0] == 0:
		x=float(result[0][0])
		res="Normal MRI study of brain tumor"
		return jsonify({'canser':res,"No":0,'new file Name':newfileName}),200
	else:
		x=float(result[0][0])
		res="there is a tumor in your brain"
		return jsonify({'canser':res,"No":1,'new file Name':newfileName}),200
	
	# for  pred in  preds:
	#     (mask, withoutMask) = pred
	# # return pred
	# if mask > withoutMask:
	#     print(mask)
	#     # return 'done'
	#     # return jsonify({'Mask': mask})
	# else :
	#     print('no mask')
	#     return jsonify({'withoutMask': float(withoutMask)}),200







# model2
import tensorflow as tf
import numpy as np
from PIL import Image
# from google.colab import files
import cv2
model = tf.keras.models.load_model('./New_Model1.h5')


#Function to preprocess the image
def preprocess_image(image_path):
    img = Image.open(image_path).resize((140, 140))
    
    # Convert PIL Image to NumPy array
    img = np.array(img)
    
    # Convert the image to grayscale using OpenCV
    img_gray = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)
    
    # Normalize pixel values
    img_gray = img_gray / 255.0  
    
    # Add batch dimension
    img_gray = np.expand_dims(img_gray, axis=0)  
    
    return img_gray
def predict_stroke(image_path,newfileName):
    img = preprocess_image(image_path)
    prediction = model.predict(img)
    if prediction[0] > 0.5:
        # x=float(result[0][0])
        res="There is brain stroke in you brain"
        return {'canser':res,"No":1,'new file Name':newfileName}
        # return "The image contains a stroke."
    else:
        res="Normal CT Study of brain"
        return {'canser':res,"No":0,'new file Name':newfileName}
        # return "The image does not contain a stroke."



@app.route('/predict2', methods=['POST'])
def predict2():
    
    # return "Hello"
    # if request.method == 'POST':
	# -------------------------------------------------------------------------------
	# if 'image' not in request.files:
	#     return jsonify({'error':'media required'}),400
	# ------------------------------------------------------------------------------------------
	# Get image data from request
	file=request.files['image']
	textt=request.content_type
	print(textt)
	vll=request.headers
	print(vll)
	strr=get_file_extension(file.filename)
	unique_filename = generate_unique_filename()
	newfileName =unique_filename + "-file." + strr
	file.filename=newfileName
	# -------------------------------------------------------------------------------------------
	filename = secure_filename(file.filename)
	file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
	# ------------------------------------------------------------------------------------------
	# image_file = cv2.imread("upload/"+file.filename)
	result = predict_stroke("upload/"+file.filename,newfileName)
	return jsonify(result),200
	# ----------------------------------------------------------------------------
	# if result:
	

@app.route('/upload/<name>')
def display_file(name):
    return send_from_directory(app.config["UPLOAD_FOLDER"], name)
    
if __name__ == 'main':
    app.debug=True
    app.run(host='0.0.0.0', port=5000)  # Listen on all interfaces, port 5000


# #@title Evaluaton
# #@markdown The avilable datasets are:
# #@markdown * [k1](https://www.kaggle.com/datasets/navoneel/brain-mri-images-for-brain-tumor-detection) - 253 samples.
# EVALUATION_DATASET = "k1" #@param ["k1", "br35h"] {allow-input: true}

# SAMPLES = []

# match EVALUATION_DATASET:
#   case "k1":
#     DIR = "eval_data"
#     download_kaggle_ds(DIR, "navoneel/brain-mri-images-for-brain-tumor-detection")
#     rmtree(f"{DIR}/brain_tumor_dataset")
#     SAMPLES.extend([(path, 0) for path in copy_samples(f"{DIR}/no")])
#     SAMPLES.extend([(path, 1) for path in copy_samples(f"{DIR}/yes")])

#   case "br35h":
#     n, y = br35h()
#     SAMPLES.extend([(path, 0) for path in n])
#     SAMPLES.extend([(path, 1) for path in y])

#   case _:
#     raise ValueError("The choosen database dosn't exist")

# print(f"Number of evaluation samples {len(SAMPLES)}")

# SAMPLES = chunk_list(SAMPLES, 1000)

# random.shuffle(SAMPLES)








# -------------------------------SecondCode----------------------------------------------------------------





# import io
# from PIL import Image
# import numpy as np
# import cv2  # Assuming OpenCV for image processing

# from tensorflow.keras.applications.mobilenet_v2 import preprocess_input
# from tensorflow.keras.preprocessing.image import img_to_array
# from tensorflow.keras.models import load_model

# # from imutils.video import VideoStream
# import numpy as np
# # import imutils
# # import time
# # import cv2
# # import os
# from flask import Flask, request, jsonify
# import os
# from flask import Flask, flash, request, redirect, url_for
# from werkzeug.utils import secure_filename
# # import numpy as np
# # import cv2  # Assuming OpenCV for image processing

# # (Your ML model loading and prediction logic here)
# def detect_and_predict_mask(frame, faceNet, maskNet):
# 	# grab the dimensions of the frame and then construct a blob
# 	# from it
# 	(h, w) = frame.shape[:2]
# 	blob = cv2.dnn.blobFromImage(frame, 1.0, (224, 224),
# 		(104.0, 177.0, 123.0))

# 	# pass the blob through the network and obtain the face detections
# 	faceNet.setInput(blob)
# 	detections = faceNet.forward()
# # 	print(detections.shape)

# 	# initialize our list of faces, their corresponding locations,
# 	# and the list of predictions from our face mask network
# 	faces = []
# 	locs = []
# 	preds = []

# 	# loop over the detections
# 	for i in range(0, detections.shape[2]):
# 		# extract the confidence (i.e., probability) associated with
# 		# the detection
# 		confidence = detections[0, 0, i, 2]

# 		# filter out weak detections by ensuring the confidence is
# 		# greater than the minimum confidence
# 		if confidence > 0.5:
# 			# compute the (x, y)-coordinates of the bounding box for
# 			# the object
# 			box = detections[0, 0, i, 3:7] * np.array([w, h, w, h])
# 			(startX, startY, endX, endY) = box.astype("int")

# 			# ensure the bounding boxes fall within the dimensions of
# 			# the frame
# 			(startX, startY) = (max(0, startX), max(0, startY))
# 			(endX, endY) = (min(w - 1, endX), min(h - 1, endY))

# 			# extract the face ROI, convert it from BGR to RGB channel
# 			# ordering, resize it to 224x224, and preprocess it
# 			face = frame[startY:endY, startX:endX]
# 			face = cv2.cvtColor(face, cv2.COLOR_BGR2RGB)
# 			face = cv2.resize(face, (224, 224))
# 			face = img_to_array(face)
# 			face = preprocess_input(face)

# 			# add the face and bounding boxes to their respective
# 			# lists
# 			faces.append(face)
# 			locs.append((startX, startY, endX, endY))

# 	# only make a predictions if at least one face was detected
# 	if len(faces) > 0:
# 		# for faster inference we'll make batch predictions on all
# 		# faces at the same time rather than one-by-one predictions
# 		# in the above for loop
# 		faces = np.array(faces, dtype="float32")
# 		preds = maskNet.predict(faces, batch_size=32)

# 	# return a 2-tuple of the face locations and their corresponding
# 	# locations
# 	return (locs, preds)

# # load our serialized face detector model from disk
# prototxtPath = r"face_detector\deploy.prototxt"
# weightsPath = r"face_detector\res10_300x300_ssd_iter_140000.caffemodel"
# faceNet = cv2.dnn.readNet(prototxtPath, weightsPath)
# # ---------------------------------------------------------------------------------------------------------------------
# # load the face mask detector model from disk
# maskNet = load_model("mask_detector.model")
# # -----------------------------------------------------------------------------------------------------------------------
# UPLOAD_FOLDER = './upload'
# ALLOWED_EXTENSIONS = set(['txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'])

# app = Flask(__name__)
# # ---------------------------------------------------------------------
# app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
# # ---------------------------------------------------------------------

# # @app.route('/')
# @app.route('/predict', methods=['POST'])
# def predict():
    
#     # return "Hello"
#     # if request.method == 'POST':
		
#         # -------------------------------------------------------------------------------
#         # if 'image' not in request.files:
#         #     return jsonify({'error':'media required'}),400
#         file=request.files['image']
#         textt=request.content_type
#         print(textt)
#         vll=request.headers
#         print(vll)
#         # return jsonify({'name of file':file.filename}),200
#         # ------------------------------------------------------------------------------------------
#         # if user does not select file, browser also
#         # submit a empty part without filename
#         # -------------------------------------------------------------------------------------------
#         # if file and allowed_file(file.filename):
#         filename = secure_filename(file.filename)
#         file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
#         # ------------------------------------------------------------------------------------------
#         # Get image data from request	

#         image_file = cv2.imread("./upload/"+file.filename)
#          # Read the file stream into a PIL Image
#         # image = Image.open(io.BytesIO(file.read()))
        
#         # -------------------------------------------------------------------------------------------
#         # img = Image.open(image_file.stream)
#         # return image_file.filename
#         # cv2.imshow(image_file)
#         # return jsonify({'name of file':image_file}),200
# # ----------------------------------------------------------------------------------------------------
        
#         image_array = np.array(image_file, dtype="float32")
#         # image_array = np.array(image, dtype="float32")

#         # # # Make prediction using your ML model
#         (locs, preds) = detect_and_predict_mask(image_array, faceNet, maskNet)
# 		# ----------------------------------------------------------------------------
#         # return jsonify({'name of file':'Done'}),200
# 		# -----------------------------------------------------------------------------
#         # # preds
#         for  pred in  preds:
#             (mask, withoutMask) = pred
#         # return pred
#         if mask > withoutMask:
#             print(mask)
#             # return 'done'
#             # return jsonify({'Mask': mask})
#             return jsonify({'Mask':float(mask)}),200

#         else :
#             print('no mask')
#             return jsonify({'withoutMask': float(withoutMask)}),200
        
# if __name__ == 'main':
#     app.debug=True
#     app.run(host='0.0.0.0', port=5000)  # Listen on all interfaces, port 5000
# # --------------------------------------------------------------------------------------------------------
        
#     # return 'Method not allowed', 405

# # ------------------------------------------------------------------------------------------------------------------------------------------------

# # @app.route('/shrook', methods=["POST","GET"])
# # def predii():
    
# #     if request.method == 'GET':
# #         #    return "Hello"
# #           return jsonify({'withoutMask': "HELLO"}),200
# #     else:
# #           file=request.files['image']
# #           print(file.filename)
# #         #   return"hii shrook"
# #           return jsonify({'withoutMask': "hii I have this Photo"}),200

# # if __name__ == 'main':
# #     app.debug=True
# #     app.run(host='0.0.0.0', port=5000)  # Listen on all interfaces, port 5000


























# -------------------------------------------------Frist Code--------------------------------------------------









# import numpy as np
# import cv2  # Assuming OpenCV for image processing

# from tensorflow.keras.applications.mobilenet_v2 import preprocess_input
# from tensorflow.keras.preprocessing.image import img_to_array
# from tensorflow.keras.models import load_model
# # from imutils.video import VideoStream
# import numpy as np
# # import imutils
# # import time
# # import cv2
# # import os
# from flask import Flask, request, jsonify
# # import numpy as np
# # import cv2  # Assuming OpenCV for image processing

# # (Your ML model loading and prediction logic here)
# def detect_and_predict_mask(frame, faceNet, maskNet):
# 	# grab the dimensions of the frame and then construct a blob
# 	# from it
# 	(h, w) = frame.shape[:2]
# 	blob = cv2.dnn.blobFromImage(frame, 1.0, (224, 224),
# 		(104.0, 177.0, 123.0))

# 	# pass the blob through the network and obtain the face detections
# 	faceNet.setInput(blob)
# 	detections = faceNet.forward()
# # 	print(detections.shape)

# 	# initialize our list of faces, their corresponding locations,
# 	# and the list of predictions from our face mask network
# 	faces = []
# 	locs = []
# 	preds = []

# 	# loop over the detections
# 	for i in range(0, detections.shape[2]):
# 		# extract the confidence (i.e., probability) associated with
# 		# the detection
# 		confidence = detections[0, 0, i, 2]

# 		# filter out weak detections by ensuring the confidence is
# 		# greater than the minimum confidence
# 		if confidence > 0.5:
# 			# compute the (x, y)-coordinates of the bounding box for
# 			# the object
# 			box = detections[0, 0, i, 3:7] * np.array([w, h, w, h])
# 			(startX, startY, endX, endY) = box.astype("int")

# 			# ensure the bounding boxes fall within the dimensions of
# 			# the frame
# 			(startX, startY) = (max(0, startX), max(0, startY))
# 			(endX, endY) = (min(w - 1, endX), min(h - 1, endY))

# 			# extract the face ROI, convert it from BGR to RGB channel
# 			# ordering, resize it to 224x224, and preprocess it
# 			face = frame[startY:endY, startX:endX]
# 			face = cv2.cvtColor(face, cv2.COLOR_BGR2RGB)
# 			face = cv2.resize(face, (224, 224))
# 			face = img_to_array(face)
# 			face = preprocess_input(face)

# 			# add the face and bounding boxes to their respective
# 			# lists
# 			faces.append(face)
# 			locs.append((startX, startY, endX, endY))

# 	# only make a predictions if at least one face was detected
# 	if len(faces) > 0:
# 		# for faster inference we'll make batch predictions on *all*
# 		# faces at the same time rather than one-by-one predictions
# 		# in the above `for` loop
# 		faces = np.array(faces, dtype="float32")
# 		preds = maskNet.predict(faces, batch_size=32)

# 	# return a 2-tuple of the face locations and their corresponding
# 	# locations
# 	return (locs, preds)

# # load our serialized face detector model from disk
# prototxtPath = r"face_detector\deploy.prototxt"
# weightsPath = r"face_detector\res10_300x300_ssd_iter_140000.caffemodel"
# faceNet = cv2.dnn.readNet(prototxtPath, weightsPath)

# # load the face mask detector model from disk
# maskNet = load_model("mask_detector.model")

# app = Flask(__name__)
# @app.route('/')
# @app.route('/predict', methods=['POST'])
# def predict():
#     # return "Hello"
#     if request.method == 'POST':
#         # Get image data from request
#         image_file = request.files['image']
#         image_array = np.array(image_array, dtype="float32")

#         # Make prediction using your ML model
#         (locs, preds) = detect_and_predict_mask(image_bytes, faceNet, maskNet)
#         preds
#         for  pred in  preds:
#                 (mask, withoutMask) = pred
#         if mask > withoutMask:
#             print('mask')
#             return jsonify({'Mask': mask})
#         else :
#             print('no mask')
#             return jsonify({'withoutMask': withoutMask})
        
#     return 'Method not allowed', 405

# if __name__ == '_main_':
#     app.debug=True
#     app.run(host='0.0.0.0', port=5000)  # Listen on all interfaces, port 5000