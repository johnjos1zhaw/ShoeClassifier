package ch.zhaw.johnjos1.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ai.djl.ModelException;
import ai.djl.modality.Classifications;
import ai.djl.translate.TranslateException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    public class InferenceController {

        @PostMapping(path = "/analyze")
        public String predict(@RequestParam("image") MultipartFile image) throws ModelException, TranslateException, IOException {
            Inference inference = new Inference();
            return inference.predict(image.getBytes()).toJson();
        }

        @GetMapping("/results")
        public ResponseEntity<String> getResults(@RequestParam("image") MultipartFile file) throws ModelException, TranslateException, IOException {
            Inference inference = new Inference();
            byte[] imageBytes = file.getBytes();
            Classifications result = inference.predict(imageBytes);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toJson());
        }
    }
}
