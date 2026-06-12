package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // Find promotions that are active on a given date.
    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate startDate,
            LocalDate endDate
    );

    // Optional: Find promotion by code.
    Optional<Promotion> findByCodeIgnoreCase(String code);

    // Optional: Find Promotions starting after a given date.
    List<Promotion> findByStartDateAfter(LocalDate date);

    // Optional: Find promotions ending before a given date.
    List<Promotion> findByEndDateBefore(LocalDate date);

    // Optional: Find promotions that have no end date.
    List<Promotion> findByEndDateIsNull();

    // Optional: Find promotions active today.
    default List<Promotion> findActiveToday() {
        LocalDate today = LocalDate.now();
        return findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today);
    }
}
