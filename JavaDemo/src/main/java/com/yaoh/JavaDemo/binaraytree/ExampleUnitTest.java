package com.yaoh.JavaDemo.binaraytree;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public static void main(String[] args) {
        BinarayTree binarayTree=new BinarayTree("A");
        binarayTree.createTree();
        binarayTree.midOrderTraverse(binarayTree.root);

//        binarayTree.preOrderTraverse(binarayTree.root);
//        binarayTree.postOrderTraverse(binarayTree.root);
    }


}