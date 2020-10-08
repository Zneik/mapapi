package ru.zneik.mapapi.util;

import ru.zneik.mapapi.exception.IllegalRequestDataException;
import ru.zneik.mapapi.exception.NotFoundException;
import ru.zneik.mapapi.model.base.HasUuid;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ValidationUtil {

    public static Throwable getRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    public static void checkNew(HasUuid bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException("Not new");
        }
    }

    public static void checkNew(Iterable<? extends HasUuid> beans) {
        beans.forEach(ValidationUtil::checkNew);
    }

    public static void assureUuidConsistent(HasUuid bean, UUID uuid) {
        if (bean.isNew()) {
            bean.setUuid(uuid);
        } else if (!bean.getUuid().equals(uuid)) {
            throw new IllegalRequestDataException("Uuids not equals");
        }
    }

    public static <T> T checkExist(Optional<T> bean) {
        return bean.orElseThrow(NotFoundException::new);
    }

}
