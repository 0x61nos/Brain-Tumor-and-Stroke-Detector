
# Directory and File Description
<!-------------------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
├── Content/
│   ├── Chapters/
│   │   ├── abstract.tex
│   │   ├── acronyms.tex
│   │   ├── appendix.tex
│   │   ├── chapter-01.tex
│   │   ├── chapter-02.tex
│   │   ├── chapter-03.tex
│   │   ├── chapter-04.tex
│   │   ├── chapter-05.tex
│   │   ├── chapter-06.tex
│   │   ├── chapter-07.tex
│   │   ├── chapter-08.tex
│   │   ├── chapter-09.tex
│   │   └── chapter-10.tex
│   └── Other_pages/
│       ├── front_matter.tex
│       └── title_page.tex
├── Img/
│   ├── Chap-01/
│   │   └── pic_01.jpg
│   ├── Chap-02/
│   ├── Chap-03/
│   ├── Logo/
│   │   └── Durham_Uni_logo.jpg
├── README.md
├── config.sty
├── refs.bib
└── thesis.tex
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-------------------------------------------------------------------------------------->

### thesis.tex
The main $\LaTeX$ file that compiles the entire thesis. It includes various chapters and sections as described above.

### Chapters/
- `abstract.tex`: Contains the abstract of the thesis in both English and other required languages.
- `acronyms.tex`: Lists all the acronyms used in the thesis.
- `appendix.tex`: Contains the appendices.
- `chapter-01.tex` to `chapter-10.tex`: Subsequent chapters covering various parts of the thesis such as introduction, literature review, methodology, results, discussion, future work, and conclusions.

### refs.bib
The bibliography file in *BibTeX* format, containing all the references cited in the thesis.

### Chap-01/
- Put the images you need to use in each chapter in the corresponding directory, if you don't find the corresponding directory, please create your own.

### Logo/
- `Durham_Uni_logo.jpg`: The logo of Durham University, used in the `title_page.tex`.




<!-------------------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Don't need to touch them unless you know what you're doing.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-------------------------------------------------------------------------------------->
### Other_pages/
- `front_matter.tex`: Includes the front matter of the thesis such as the `title_page.tex`, table of contents.
- `title_page.tex`: Contains the title page information.

### config.sty
This file contains custom $\LaTeX$ configurations and package imports used throughout the thesis.

## How to Compile

To compile the thesis, ensure you have a LaTeX distribution installed. Open `thesis.tex` in your LaTeX editor and compile it. The included `config.sty` file should handle all necessary configurations and package imports.


