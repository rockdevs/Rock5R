package context.abstracts;

import java.io.IOException;

@FunctionalInterface
public interface Component {
     void init() throws IOException;
}
