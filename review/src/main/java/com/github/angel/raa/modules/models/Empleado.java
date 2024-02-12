package com.github.angel.raa.modules.models;

import java.util.Set;

public class Empleado {
    private String name;
    private Set<String > proyectos;

    public Empleado() {
    }

    public Empleado(String name, Set<String> proyectos) {
        this.name = name;
        this.proyectos = proyectos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<String> proyectos) {
        this.proyectos = proyectos;
    }
}
