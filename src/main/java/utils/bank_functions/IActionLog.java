package utils.bank_functions;

import java.io.Serializable;
import java.util.List;

public interface IActionLog {
    void actionLog();
    void creationLog(List list);
}
