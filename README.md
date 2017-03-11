# PDFLayoutTextStripper as docker container command-line utility

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)]()
[![](https://images.microbadger.com/badges/image/madnight/pdf-layout-text-stripper.svg)](https://microbadger.com/images/madnight/pdf-layout-text-stripper "Get your own image badge on microbadger.com")
[![](https://travis-ci.org/madnight/pdf-layout-text-stripper.svg)](https://travis-ci.org/madnight/pdf-layout-text-stripper)
[![Code Climate](https://lima.codeclimate.com/github/madnight/pdf-layout-text-stripper/badges/gpa.svg)](https://lima.codeclimate.com/github/madnight/pdf-layout-text-stripper)
[![Issue Count](https://lima.codeclimate.com/github/madnight/pdf-layout-text-stripper/badges/issue_count.svg)](https://lima.codeclimate.com/github/madnight/pdf-layout-text-stripper)

Converts a PDF file into a text file while keeping the layout of the original PDF. Useful to extract the content from a table or a form in a PDF file. PDFLayoutTextStripper is a subclass of PDFTextStripper class (from the [Apache PDFBox](https://pdfbox.apache.org/) library).

* Use cases
* How to use

## Use cases
Data extraction from a table in a PDF file
![example](https://i.imgur.com/6z8OG2O.png)
-
Data extraction from a form in a PDF file
![example](https://i.imgur.com/JB7PxKJ.png)

## How to use

```bash
# i do it myself
docker build -t pdf-layout-text-stripper .
docker run -v $(pwd):/app pdf-layout-text-stripper "sample.pdf"

# i'm lazy
docker run -v $(pwd):/app madnight/pdf-layout-text-stripper "sample.pdf"
```
