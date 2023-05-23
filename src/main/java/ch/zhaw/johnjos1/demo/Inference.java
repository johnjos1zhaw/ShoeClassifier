package ch.zhaw.johnjos1.demo;

import ai.djl.Model;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.transform.ToTensor;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Inference {
    private Predictor<Image, Classifications> predictor;

    public Inference() throws ModelException, IOException {
        // the location where the model is saved
        Path modelDir = Paths.get("models");

        // Load the model
        Model model = Models.getModel();
        model.load(modelDir, Models.MODEL_NAME);

        // Define a translator for pre and post processing
        Translator<Image, Classifications> translator =
                ImageClassificationTranslator.builder()
                        .addTransform(new Resize(Models.IMAGE_WIDTH, Models.IMAGE_HEIGHT))
                        .addTransform(new ToTensor())
                        .optApplySoftmax(true)
                        .build();

        // Initialize the Predictor object
        predictor = model.newPredictor(translator);
    }

    // Prediction on byte array
    public Classifications predict(byte[] image) throws ModelException, TranslateException, IOException {
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage bi = ImageIO.read(is);
        Image img = ImageFactory.getInstance().fromImage(bi);

        Classifications predictResult = predictor.predict(img);
        return predictResult;
    }

    public static void main(String[] args) throws ModelException, TranslateException, IOException {
        Inference inference = new Inference();
        byte[] imageBytes = Files.readAllBytes(Paths.get("C:/Users/j17jo/Downloads/ut-zap50k-images-square/ut-zap50k-images-square/Shoes/Heels/Vivanz/7493935.30306.jpg"));
        Classifications result = inference.predict(imageBytes);
        System.out.println(result);
    }
}
