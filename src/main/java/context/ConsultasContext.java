package context;

import models.Consulta;

public final class ConsultasContext {
    private static ConsultasContext instance;
    private Boolean edit = false;
    private Consulta employee;

    private ConsultasContext() {
    }

    public static ConsultasContext getInstance() {
        if (instance == null) {
            instance = new ConsultasContext();
        }

        return instance;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Consulta getEmployee() {
        return employee;
    }

    public void setEmployee(Consulta employee) {
        this.employee = employee;
    }

    public void clear() {
        this.employee = null;
        this.edit = false;
    }
}
