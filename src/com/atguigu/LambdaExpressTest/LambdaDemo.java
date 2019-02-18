package com.atguigu.LambdaExpressTest;

public class LambdaDemo {
    public static void main(String[] args) {
        //lambda 表达式
        Foo foo = (x,y)->{
            return x + y;
        };

        System.out.println(foo.add(1,2));
        System.out.println(foo.mul(1,2));
        System.out.println(Foo.div(2,2));
    }
}

@FunctionalInterface
interface Foo{
    public int add(int x, int y);

    //接口中定义方法除了只有方法头之外，还可以有默认方法，用default，static 修饰的方法，且可以有方法体
    public default int mul(int x, int y){
        return x * y;
    }

    public static int div(int x, int y){
        return x / y;
    }
}