package qqzsh.top.search.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;
/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 16:31
 * @Description
 */
public enum OpType {
    ADD,
    UPDATE,
    DELETE,
    OTHER;

    public static OpType to(EventType eventType) {

        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;

            default:
                return OTHER;
        }
    }
}
