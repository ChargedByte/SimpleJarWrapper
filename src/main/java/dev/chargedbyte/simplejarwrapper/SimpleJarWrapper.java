package dev.chargedbyte.simplejarwrapper;

import dev.chargedbyte.simplejarwrapper.config.SimpleJarWrapperConfig;

import java.io.File;
import java.io.IOException;

public class SimpleJarWrapper {
    public static void main(String[] args) {
        File file = new File("wrapper.yaml");

        if (!file.exists()) {
            file = new File("wrapper.yml");

            if (!file.exists()) {
                System.out.println("No configuration file found.");
                System.exit(1);
            }
        }

        SimpleJarWrapperConfig config = SimpleJarWrapperConfig.load(file);

        if (config.getVersion() != 1) {
            System.out.println("Unsupported configuration version.");
            System.exit(1);
        }

        Process subprocess = null;
        try {
            subprocess = new ProcessBuilder(config.getCommand()).inheritIO().start();
        } catch (IOException ex) {
            System.out.println("Failed to start the subprocess.");
            ex.printStackTrace();
            System.exit(1);
        }

        int exitCode = 0;
        try {
            exitCode = subprocess.waitFor();
        } catch (InterruptedException ex) {
            System.out.println("SimpleJarWrapper was interrupted.");
            ex.printStackTrace();
            System.exit(1);
        }

        System.exit(exitCode);
    }
}
