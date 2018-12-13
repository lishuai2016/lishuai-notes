package com.ls.design_pattern.abstractFactory;

/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2018-12-03 23:36
 */
public class ConcreteFactory2 extends AbstractFactory {
    @Override
    AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    AbstractProductB createProductB() {
        return new ProductB2();
    }
}
