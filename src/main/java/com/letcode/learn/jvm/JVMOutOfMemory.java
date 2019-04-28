package com.letcode.learn.jvm;

import java.util.ArrayList;
import java.util.List;

public class JVMOutOfMemory {

    public static void main(String[] args) {
        List<OOMObj> oomObjs = new ArrayList<>();
        while (true){
            oomObjs.add(new OOMObj());
        }

    }

    static class OOMObj{

    }
}
