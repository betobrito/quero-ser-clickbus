package br.com.clickbus.places.cucumber.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class HelperContextImpl implements HelperContext {

    private JdbcTemplate jdbcTemplate;

    public HelperContextImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void clearTestData() {
        jdbcTemplate.update("delete from public.place");
    }

    @Override
    public void insertPlace(long id, String name, String slug, String city) {
        jdbcTemplate.update("insert into public.place (id, name, slug, city, created_at, updated_at) values (?,?,?,?,current_timestamp, current_timestamp)", id, name, slug, city);
    }

}
