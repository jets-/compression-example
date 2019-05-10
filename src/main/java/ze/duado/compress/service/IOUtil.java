package ze.duado.compress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ze.duado.compress.IOProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class IOUtil {

    private final IOProperties ioProperties;

    @Autowired
    public IOUtil(IOProperties ioProperties) {
        this.ioProperties = ioProperties;
    }

    public byte[] read() {
        File inputFile = new File(ioProperties.getDefault().getInput());
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(inputFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public byte[] read(String customInput) {
        File inputFile = new File(customInput);
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(inputFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void write(byte[] data) {
        try (FileOutputStream stream = new FileOutputStream(ioProperties.getDefault().getOutput())) {
            stream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] data, String customOutput) {
        File outputFile = new File(customOutput);
        try (FileOutputStream stream = new FileOutputStream(outputFile)) {
            stream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
