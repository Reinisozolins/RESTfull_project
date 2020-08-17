package lv.ozo.awsspringtest.ui.shared;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {
    public String generatedUserID() {
        return UUID.randomUUID().toString();
    }
}
