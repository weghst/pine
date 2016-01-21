package com.weghst.pine.ldap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;

import java.io.IOException;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LdapTest extends SpringTestSupport {

    public static final String BASE_DN = "dc=mychebao,dc=com";

    @Autowired
    private LdapTemplate ldapTemplate;

    @Test
    public void testAa() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new SmileFactory());

        Config config = new Config();
        config.setComments("HELLO");
        config.setKey("Key");
        config.setValue("Value");

        byte[] s = objectMapper.writeValueAsBytes(config);
        System.out.println(new String(s));

        Config config1 = objectMapper.readValue(s, Config.class);
        System.out.println(config1);
    }

    @Test
    public void testFindAll() {
        ldapTemplate.search(query(), new AttributesMapper<String>() {
            @Override
            public String mapFromAttributes(Attributes attributes) throws NamingException {
                System.out.println(attributes);
                return null;
            }
        });
    }

    @Test
    public void testBind() {
        // RDN
        LdapName ldapName = LdapNameBuilder.newInstance()
                .add("cn", "Kevin2")
                .build();

        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute("objectClass");

        ocattr.add("top");
        ocattr.add("person");

        attrs.put(ocattr);

        // 属性
        attrs.put("telephoneNumber", "13085162323");
        attrs.put("cn", "Kevin9999");
        attrs.put("sn", "GGGGGGGGG");

        ldapTemplate.bind(ldapName, null, attrs);
    }

}
