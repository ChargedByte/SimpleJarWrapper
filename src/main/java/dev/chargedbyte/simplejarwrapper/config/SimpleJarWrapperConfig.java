package dev.chargedbyte.simplejarwrapper.config;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class SimpleJarWrapperConfig {
    @NonNull
    private Integer version;
    @NonNull
    private Wrapper wrapper;

    public static SimpleJarWrapperConfig load(File path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            return mapper.readValue(path, SimpleJarWrapperConfig.class);
        } catch (IOException ex) {
            if (ex.getCause() instanceof StreamReadException) {
                System.out.println("Configuration file is not valid YAML.");
                ex.printStackTrace();
                System.exit(1);
            }

            if (ex.getCause() instanceof DatabindException) {
                System.out.println("Configuration file is not valid.");
                ex.printStackTrace();
                System.exit(1);
            }

            System.out.println("Failed to read the configuration file.");
            ex.printStackTrace();
            System.exit(1);
        }

        // Unreachable
        return null;
    }

    public List<String> getCommand() {
        List<String> command = new ArrayList<>();

        command.add((wrapper.getJavaPath() != null) ? wrapper.getJavaPath().toString() : "java");

        if (wrapper.getMinMemory() != null) {
            command.add("-Xms" + wrapper.getMinMemory());
        }

        if (wrapper.getMaxMemory() != null) {
            command.add("-Xmx" + wrapper.getMaxMemory());
        }

        if (wrapper.getJavaArgs() != null) {
            command.addAll(wrapper.getJavaArgs());
        }

        command.add("-jar");
        command.add(wrapper.getJarPath().toString());

        if (wrapper.getJarArgs() != null) {
            command.addAll(wrapper.getJarArgs());
        }

        return command;
    }
}
