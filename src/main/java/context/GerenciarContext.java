package context;

import models.Funcionario;

public final class GerenciarContext {
    private static GerenciarContext instance;
    private Boolean edit = false;
    private Funcionario employee;

    private GerenciarContext() {}

    public static GerenciarContext getInstance() {
        if (instance == null) {
            instance = new GerenciarContext();
        }

        return instance;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Funcionario getEmployee() {
        return employee;
    }

    public void setEmployee(Funcionario employee) {
        this.employee = employee;
    }

    public void clear(){
        this.employee = null;
        this.edit = false;
    }
}
