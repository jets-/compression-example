package ze.duado.compress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CompressApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(CompressApplication.class);

    private final CompressManager manager;

    @Autowired
    public CompressApplication(CompressManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CompressApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);
        app.run(args);
        LOG.info("FINISHED");
    }

    @Override
    public void run(String... args) {
        if(args != null && args.length != 0)  {
            String[] io = Arrays.copyOfRange(args, 1, args.length);
            switch (args[0]) {
                case "lz4":
                    manager.lz4(getInput(io), getOutput(io));
                    break;
                case "zstd":
                    manager.zstd(getInput(io), getOutput(io));
                    break;
                case "full":
                    manager.fullTest();
                    break;
                default:
                    manager.all(getInput(args), getOutput(args));
                    break;
            }
        } else
            manager.all(null, null);
    }

    private String getInput(String... args) {
        String input = null;
        if(args.length > 0 && args[0] != null && !args[0].equals("")) {
            input = args[0];
        }
        return input;
    }

    private String getOutput(String... args) {
        String output = null;
        if(args.length > 1 && args[1] != null && !args[1].equals("")) {
            output = args[1];
        }
        return output;
    }






}
