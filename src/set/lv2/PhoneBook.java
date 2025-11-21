package set.lv2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/** 전화번호 목록 - 프로그래머스 42577
 *
 * 문제 유형 : 해시, Set, Sort
 */
public class PhoneBook {

    // 1. 길이 기준 정렬 + 이중 for문 - 시간 초과
    // 시간 복잡도 : O(n²)
    // 모든 문자열끼리 비교
    //    - n개 중 2개씩 다 비교 → n²
    //    - 비교 하나당: `startsWith` → O(m)
    public boolean solution1(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.comparingInt(String::length));

        for(int i = 0; i < phone_book.length - 1; i++) {
            String prefix = phone_book[i];

            for(int j = i + 1; j < phone_book.length; j++) {
                if(phone_book[j].startsWith(prefix)) return false;
            }
        }

        return true;
    }


    // 2. 사전순 정렬 + 단일 for문 - 추천
    // 시간 복잡도 : O(n log n)
    // 정렬 + 바로 다음만 비교가 정답
    public boolean solution2(String[] phone_book) {
        Arrays.sort(phone_book);

        for(int i = 0; i < phone_book.length - 1; i++) { // n번
            if(phone_book[i + 1].startsWith(phone_book[i])) return false;
        }

        return true;
    }


    // 3. 해시 - 문제 의도
    // 시간 복잡도 : O(n * m)
    // 다른 문자열이 아니라 자기 자신만 봄
    //    - 모든 번호를 Set에 넣어놓고
    //    - 각 문자열마다 prefix만 확인 → m번
    //    - O(n * m)
    public boolean solution3(String[] phone_book) {
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));

        for(String phone : phone_book) { // n
            for(int i = 1; i < phone.length(); i++) {  // m
                String prefix = phone.substring(0, i); // 문자열의 접두사

                if(set.contains(prefix)) return false; // O(1)
            }
        }

        return true;
    }
}
