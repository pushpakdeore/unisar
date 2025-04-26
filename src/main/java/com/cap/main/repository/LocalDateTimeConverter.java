
package com.cap.main.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts LocalDateTime to SQL Timestamp when persisting to the database
 * and converts SQL Timestamp back to LocalDateTime when reading from the database.
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    /**
     * Converts LocalDateTime to Timestamp for database persistence.
     *
     * @param attribute the LocalDateTime to convert.
     * @return the corresponding Timestamp.
     */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Timestamp.valueOf(attribute);
    }

    /**
     * Converts Timestamp from the database back to LocalDateTime.
     *
     * @param dbData the Timestamp from the database.
     * @return the corresponding LocalDateTime.
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime();
    }
}
