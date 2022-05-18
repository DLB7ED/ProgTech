package edu.ekke.yii8yw.models;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.core.database.MySqlDriver;
import edu.ekke.yii8yw.helpers.Tools;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeAll
    static void beforeAll() {
        DB.init(new MySqlDriver());
        DB.getInstance()
                .execute("insert into users (name, email, password) values (?, ?, ?)",
                        Tools.asList("Initial Test User", "test@init.com", "----"));
    }

    @AfterAll
    static void afterAll() {
        DB.getInstance().execute("delete from users where email like 'test@%'", new ArrayList<>());
    }

    @Test
    void save() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@test.com");
        user.setPassword("password");
        Boolean res = Assertions.assertDoesNotThrow(user::save);
        Assertions.assertTrue(res);

        var result = DB.getInstance().findOne("select id from users where email=?", Tools.asList(user.getEmail()));
        long id = (long) result.get("id");
        user.setId(id);
        user.setName("Test User2");
        Boolean res2 = Assertions.assertDoesNotThrow(user::save);
        Assertions.assertTrue(res2);
    }

    @Test
    void load() {
        HashMap<String, Object> result = DB.getInstance().findOne("select id from users where email=?", Tools.asList("test@init.com"));
        long id = (long) result.get("id");
        User user = new User();
        Boolean res = Assertions.assertDoesNotThrow(() -> user.load(id));
        Assertions.assertTrue(res);
        Assertions.assertEquals("Initial Test User" ,user.getName());
    }

    @Test
    void fromHash() {
        User user = new User();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id",(long) 1);
        hashMap.put("name", "Test User");
        hashMap.put("email", "test@test2.com");
        hashMap.put("password", "password2");
        hashMap.put("is_admin", true);
        hashMap.put("created_at", new Timestamp(1_652_901_863_564L));
        Boolean res = Assertions.assertDoesNotThrow(() -> user.fromHash(hashMap));
        Assertions.assertTrue(res);
        Assertions.assertEquals(1 , user.getId());
        Assertions.assertEquals("Test User", user.getName());
        Assertions.assertEquals("test@test2.com", user.getEmail());
        Assertions.assertEquals("password2", user.getPassword());
        Assertions.assertEquals(new Timestamp(1_652_901_863_564L), user.getCreatedAt());

    }

    @Test
    void toHash() {
        User user = new User();
        user.setId(2);
        user.setName("Test User2");
        user.setEmail("test@test3.com");
        user.setPassword("password3");
        user.setIsAdmin(true);
        user.setCreatedAt(new Timestamp(1_652_901_863_564L));

        var res = Assertions.assertDoesNotThrow(user::toHash);
        Assertions.assertEquals(2L, (long) res.get("id"));
        Assertions.assertEquals("Test User2", res.get("name"));
        Assertions.assertEquals("test@test3.com", res.get("email"));
        Assertions.assertEquals("password3", res.get("password"));
        Assertions.assertEquals(1, res.get("is_admin"));
        Assertions.assertEquals(new Timestamp(1_652_901_863_564L), res.get("created_at"));
    }
}