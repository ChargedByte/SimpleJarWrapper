package dev.chargedbyte.simplejarwrapper.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Wrapper {
    private File javaPath;
    private String minMemory;
    private String maxMemory;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> javaArgs;
    @NonNull
    private File jarPath;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> jarArgs;
}
