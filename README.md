# Brain Tumor and Stroke Detector

## Abstract

A brain tumor is a growth of cells in the brain or near it. Brain tumors can occur in the brain tissue or nearby locations, including nerves, the pituitary gland, the pineal gland, and the membranes that cover the surface of the brain. The rigidity of the skull means any growth can cause significant problems. Brain tumors can be either cancerous (malignant) or non-cancerous (benign).

Brain cancer is a life-threatening disease that severely affects those diagnosed. Precise classification of brain tumors is crucial for early diagnosis, which significantly increases the survival rate of patients. However, early detection is challenging, and manually evaluating magnetic resonance imaging (MRI) images is difficult. Therefore, there is a need for optimized and fast digital methods for tumor diagnosis with better accuracy.

In this project, we use a deep learning model based on convolutional neural networks (CNNs) to identify and classify brain cancer and its type from MRI images of patients using a publicly available dataset on Kaggle.

A brain stroke, often referred to as a brain attack, is a sudden interruption of blood flow to the brain and is a medical emergency. Strokes occur when a blood vessel in the brain becomes blocked or narrowed, or when a blood vessel bursts and spills blood into the brain. Stroke is a significant cause of death and disability worldwide. Prior to modern therapy, post-stroke mortality was around 10% in the acute period, with nearly half of the patients developing moderate-to-severe disability.

The most fundamental aspect of acute stroke management is "time is brain". In acute ischemic stroke, the primary therapeutic goal of reperfusion therapy, including intravenous recombinant tissue plasminogen activator (IV TPA) and/or endovascular thrombectomy, is the rapid restoration of cerebral blood flow to salvageable ischemic brain tissue at risk for cerebral infarction. Several landmark endovascular thrombectomy trials have shown benefits in selecting patients with acute stroke caused by occlusion of the proximal anterior circulation, leading to a paradigm shift in managing acute ischemic strokes.

In the modern era of acute stroke care, more patients survive with varying degrees of disability post-stroke. A comprehensive stroke rehabilitation program is critical to optimize post-stroke outcomes. Understanding the natural history of stroke recovery and adopting a multidisciplinary approach leads to improved chances for successful rehabilitation.

In this project, we use a CNN model for deep learning multi-image classification to classify input images into categories: tumor, no tumor, stroke, or no stroke.

## Table of Contents
1. [Introduction](#introduction)
2. [Dataset](#dataset)
3. [Methodology](#methodology)
4. [Implementation](#implementation)
5. [Results](#results)
6. [Conclusion](#conclusion)
7. [Future Work](#future-work)
8. [References](#references)

## Introduction
Brain tumors and strokes are critical medical conditions requiring early and accurate diagnosis. This project aims to develop an automated system using deep learning to assist in the classification and detection of these conditions from MRI images.

## Dataset
We utilized a publicly available dataset from Kaggle for this project. The dataset consists of MRI images labeled as tumor, no tumor, stroke, or no stroke.

## Methodology
We employed convolutional neural networks (CNNs) for image classification due to their effectiveness in processing and analyzing visual data. Our approach includes:
- Preprocessing the MRI images
- Building and training the CNN model
- Evaluating the model's performance

## Implementation
The project is implemented using Flask, Spring Boot, Jupyter Notebook, and Vue.js. The following steps outline the implementation process:

### Frontend
- **Vue.js**: Used to create a user-friendly interface for uploading MRI images and displaying results.

### Backend
- **Flask**: Serves as the backend framework for the deep learning model, handling image uploads, processing, and model inference.
- **Spring Boot**: Manages the application’s business logic, handles API requests, and integrates with the frontend.

### Deep Learning Model
- **Jupyter Notebook**: Used for developing and training the CNN model. The notebook includes code for data preprocessing, model architecture, training, and evaluation.

### Steps
1. **Data Preprocessing**: Normalizing and augmenting the MRI images to improve the model's robustness.
2. **Model Architecture**: Designing a CNN model tailored for multi-class classification.
3. **Training**: Using the training dataset to optimize the model's parameters.
4. **API Development**: Creating Flask endpoints to handle image uploads and return predictions.
5. **Frontend Integration**: Connecting the Vue.js frontend with the Flask and Spring Boot backends to provide seamless user interaction.

## Results
Our CNN model demonstrated promising results in classifying MRI images into the respective categories. The accuracy and other performance metrics will be discussed in detail.

## Conclusion
The developed system shows potential to assist medical professionals with the early detection and classification of brain tumors and strokes. This can lead to timely intervention and better patient outcomes.

## Future Work
Future improvements could include:
- Expanding the dataset for better model generalization
- Incorporating additional medical imaging modalities
- Enhancing the model's architecture for higher accuracy

## References
- Kaggle Brain MRI Dataset: [Link to Dataset](https://www.kaggle.com/datasets/br35h/brain-tumor-detection)
- Relevant research papers and articles on brain tumor and stroke detection using deep learning.
