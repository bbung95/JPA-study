package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberForm;
import jpabook.jpashop.service.MemberService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // member Entity를 응답으로 사용 => 엔티티의 모든 값이 노출된다.(보안) Entity 변경시 API 스펙이 변경된다.
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){

        return memberService.findMember();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2(){

        List<Member> findMembers = memberService.findMember();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static  class MemberDto{
        private String name;
    }

    // member Entity를 객체로 사용 => Entity의 직접 관여하며 Entity 변경시 API 스펙이 변경된다.
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // DTO를 객체로 사용 => DTO를 사용하여 필요한 데이터를 명시한다. API 스펙이 된다.
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
                @PathVariable Long id,
                @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    @AllArgsConstructor
    static  class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static  class UpdateMemberRequest{
        private String name;
    }

    @Data
    static  class CreateMemberRequest{
        @NotEmpty
        private String name;
    }

    @Data
    static  class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
