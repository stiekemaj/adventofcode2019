package eu.stiekema.jeroen.adventofcode2019.intcode;

public enum ParameterMode {
    POSITION("0"),
    IMMEDIATE("1"),
    RELATIVE("2");

    private String code;

    ParameterMode(String code) {
        this.code = code;
    }

    public static ParameterMode byCode(String code) {
        for (ParameterMode parameterMode : values()) {
            if (parameterMode.code.equals(code)) {
                return parameterMode;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
