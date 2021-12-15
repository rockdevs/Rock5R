package helper;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public enum ThemeChanger {
    DARK{
        @Override
        public FlatLaf get() {
            return new FlatDarkLaf();
        }
    }
    ,
    LIGHT{
        @Override
        public FlatLaf get() {
            return new FlatLightLaf();
        }
    },
    DRACULA{
        @Override
        public FlatLaf get() {
            return new FlatDarculaLaf();
        }
    }
    ;

    public abstract FlatLaf get();
}
