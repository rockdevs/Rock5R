package factory.abstracts;

import factory.concretes.ComponentFactory;
import factory.concretes.LayoutFactory;
import factory.concretes.MenuBarFactory;
import factory.concretes.WindowsFactory;

public enum FactoryManager {
    COMPONENT{
        @Override
        public Object get() {
            return new ComponentFactory();
        }
    },
    MENU_BAR{
        @Override
        public Object get() {
            return new MenuBarFactory();
        }
    },
    WINDOWS{
        @Override
        public Object get() {
            return new WindowsFactory();
        }
    },
    LAYOUT{
        @Override
        public Object get() {
            return new LayoutFactory();
        }
    };

    public abstract Object get();

}
