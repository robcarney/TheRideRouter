package cs4500.group202.interfaces.repositories;

import cs4500.group202.data.model.TripEntity;
import java.util.List;

/**
 * For the purposes of creating a data repository for trips.
 */
public interface ITripRepository {

  List<TripEntity> getAll();

  TripEntity getById(int id);



}
