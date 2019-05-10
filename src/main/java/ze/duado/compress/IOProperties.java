package ze.duado.compress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file")
@Configuration("ioProperties")
public class IOProperties {

    private String nullFile;

    private String randomFile;

    private String semiRandomFile;

    private Default aDefault;

    public String getNullFile() {
        return nullFile;
    }

    public void setNullFile(String zero) {
        this.nullFile = zero;
    }

    public String getRandomFile() {
        return randomFile;
    }

    public void setRandomFile(String randomFile) {
        this.randomFile = randomFile;
    }

    public String getSemiRandomFile() {
        return semiRandomFile;
    }

    public void setSemiRandomFile(String semiRandomFile) {
        this.semiRandomFile = semiRandomFile;
    }

    public Default getDefault() {
        return aDefault;
    }

    public void setDefault(Default aDefault) {
        this.aDefault = aDefault;
    }

    public static class Default {
        private String input;
        private String output;
        private String path;

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
