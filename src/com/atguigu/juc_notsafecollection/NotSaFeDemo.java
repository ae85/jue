package com.atguigu.juc_notsafecollection;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSaFeDemo {
    public static void main(String[] args) {

        //以下是错误代码，异常ConcurrentModificationException,高并发修改异常
      /*  List list = new ArrayList<>();
        for (int i = 0; i<30; i++) {
            new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
            },String.valueOf(i)).start();
        }*/

      //对于以上的更改,写时复制，CopyOnWriteArrayList
 /*    List list = new CopyOnWriteArrayList();
             for (int i = 0; i<30; i++) {
        new Thread(()->{
            list.add(UUID.randomUUID().toString().substring(0,4));
            System.out.println(list);
        },String.valueOf(i)).start();
    }*/

       // setNotSafe();
        mapNotSafe();

}

    public static void setNotSafe(){
         //Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i<30; i++) {
                     new Thread(()->{
                         set.add(UUID.randomUUID().toString().substring(0,4));
                         System.out.println(set);
                     },String.valueOf(i)).start();
                 }
    }

    public static void mapNotSafe(){
        //Map<String, String> map = new HashMap();
        Map<String, String> map = new ConcurrentHashMap<>();
                 for (int i = 0; i<30; i++) {
                     new Thread(()->{
                         map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,4));
                         System.out.println(map);
                     },String.valueOf(i)).start();
                 }
    }
}


/*
juc中CopyOnWriteArraySet 中的 add方法
CopyOnWrite就是写时复制的容器，当往容器中添加一个元素时，不是直接向容器Object[] snapshot 中添加，而是先将当前容器Object[]进行Copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

 public boolean addIfAbsent(E e) {
        Object[] snapshot = getArray();

        //index方法 是目标set中的null，以及重复，若是没有则返回-1，也就是意味着不用添加
        return indexOf(e, snapshot, 0, snapshot.length) >= 0 ? false :
            addIfAbsent(e, snapshot);
    }
private boolean addIfAbsent(E e, Object[] snapshot) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        //
        Object[] current = getArray();
        int len = current.length;
        if (snapshot != current) {
            // Optimize for lost race to another addXXX operation
            int common = Math.min(snapshot.length, len);
            for (int i = 0; i < common; i++)
                if (current[i] != snapshot[i] && eq(e, current[i]))
                    return false;
            if (indexOf(e, current, common, len) >= 0)
                return false;
        }
        Object[] newElements = Arrays.copyOf(current, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
*/