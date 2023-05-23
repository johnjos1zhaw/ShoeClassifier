Sure! Here's a README file for your app:

# Josh's Shoe Classifier

This application is a shoe classifier that uses deep learning to predict the category of a shoe image. It leverages the DJL (Deep Java Library) framework and a pre-trained ResNetV1 model to perform image classification.

## Prerequisites

Before running the application, ensure that you have the following:

- Java Development Kit (JDK) installed on your machine.
- Maven build tool installed on your machine.
- A dataset of shoe images for training and testing the model.

## Installation

1. Clone the repository to your local machine:

   ```shell
   git clone https://github.com/johnjos1/shoe-classifier.git
   ```

2. Navigate to the project directory:

   ```shell
   cd shoe-classifier
   ```

3. Build the project using Maven:

   ```shell
   mvn clean install
   ```

## Usage

1. Train the Shoe Classifier model by executing the `Training` class:

   ```shell
   mvn exec:java -Dexec.mainClass="ch.zhaw.johnjos1.demo.Training"
   ```

   This will train the model using the shoe dataset and save it to the `models` directory.

2. Start the application by executing the `DemoApplication` class:

   ```shell
   mvn exec:java -Dexec.mainClass="ch.zhaw.johnjos1.demo.DemoApplication"
   ```

   The application will start a web server that listens on port 8080.

3. Open your web browser and go to [http://localhost:8080](http://localhost:8080).

4. Choose an image of a shoe to upload and click the "Analyze" button.

5. The application will analyze the uploaded image using the trained model and display the classification results.

## Project Structure

- `src/main/java/ch.zhaw.johnjos1.demo`: Contains the Java source code files for the application.
  - `DemoApplication`: Main class that starts the Spring Boot application.
  - `Inference`: Class responsible for performing inference using the trained model.
  - `Models`: Helper class for loading and saving the model.
  - `Training`: Class for training the shoe classifier model using the shoe dataset.
- `src/main/resources`: Contains the HTML file for the web interface.
  - `index.html`: HTML file with the user interface for uploading and analyzing shoe images.

## Acknowledgments

This application is based on the DJL (Deep Java Library) framework. Special thanks to the DJL community for their contributions.

Please note that this application is provided as-is without any warranty. Use it at your own risk.

---