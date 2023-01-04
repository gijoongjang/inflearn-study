package hello.servlet.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository {

    private Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    private static MemberRepository instance = null;

    private MemberRepository() {}

    public static MemberRepository getInstance() {
        if(instance == null) {
            instance = new MemberRepository();
        }

        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
