package spring.jpa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.jpa.demo.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ngoc on 26/03/2018
 * @project spring-data-jpa
 */
public abstract class CommonService<T>
{
    @Autowired
    private CommonRepository<T> repository;

    public void save(T t)
    {
        repository.save(t);
    }

    public void delete(String id)
    {
        repository.findById(id).ifPresent(t -> repository.delete(t));
    }

    public Optional<T> getById(String id)
    {
        return repository.findById(id);
    }

    public Page<T> getAll(Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    public abstract List<T> search(T t);
}
