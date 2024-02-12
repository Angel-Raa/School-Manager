package com.github.angel.raa.modules.stream;


import com.github.angel.raa.modules.models.Data;

public class Map {

    public static void main(String[] args) {
     int iva = Data.ventas.stream().
             mapToInt(venta -> (int) (venta.price() * 0.19)).reduce(0, Integer::sum);
        System.out.println(iva);


    }
}
