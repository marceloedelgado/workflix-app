package tec.ispc.workflix.utils;

public class Apis {
    public static final String URL_001="http://192.168.0.125:8080/personas/";

    public static PersonaService getPersonaService(){
        return Cliente.getCliente(URL_001).create(PersonaService.class);
    }
}
