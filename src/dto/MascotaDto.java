package dto;

public class MascotaDto extends Animal {
    private String id_dueño;
    private String nombre;

    public String getId_dueño() {
        return id_dueño;
    }

    public void setId_dueño(String id_dueño) {
        this.id_dueño = id_dueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
