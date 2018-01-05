package cs4500.group202.interfaces.services;

import cs4500.group202.interfaces.model.IRequest;
import java.io.Reader;
import java.util.List;

/**
 * A service for the purposes of reading CSV data into a collection of requests.
 */
public interface ICSVDataToRequestsService {

  /**
   * Reads data froma CSV and returns a corresponding list of Requests.
   * @param reader File reader to be used.
   * @return  Corresponding list of Requests.
   * @throws Exception For improperly formatted data.
   */
  List<IRequest> readDataToRequests(Reader reader) throws Exception;

}
