package com.github.angel.raa.modules.stream;

import com.github.angel.raa.modules.models.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FlatMap {

    public static void main(String[] args) {
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoList.add(new Empleado("Jose", Set.of("a", "b")));
        empleadoList.add(new Empleado("Maria", Set.of("c", "d")));
        empleadoList.add(new Empleado("Juan", Set.of("a", "b")));
        empleadoList.add(new Empleado("Ana", Set.of("c", "d")));
        empleadoList.add(new Empleado("Pedro", Set.of("a", "b")));
        empleadoList.add(new Empleado("Luis", Set.of("c", "d")));
        empleadoList.stream()
                .flatMap(em -> em.getProyectos().stream())
                .distinct()
                .toList()
                .forEach(System.out::println);

    }
}
