package ru.zneik.mapapi.model.base;

import java.util.UUID;

public interface HasUuid {
    UUID getUuid();

    void setUuid(UUID uuid);

    default boolean isNew() {
        return this.getUuid() == null;
    }
}
