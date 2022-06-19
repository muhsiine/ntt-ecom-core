package ma.nttsquad.nttecomcore.cons;

public enum LangCons {
    EN("en"),
    FR("fr"),
    AR("ar"),
    ES("es");

    private final String code;

    LangCons(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
