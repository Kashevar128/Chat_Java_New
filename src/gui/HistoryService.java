package gui;

import java.io.IOException;
import java.util.List;

public interface HistoryService {

    void save(List<String> chat) throws IOException;

    List<String> load();
}
