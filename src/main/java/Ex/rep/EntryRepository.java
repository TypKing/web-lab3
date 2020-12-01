package Ex.rep;



import Ex.Entry;

import java.util.List;

public interface EntryRepository {

    void save(Entry e);

    List<Entry> findAll();

}