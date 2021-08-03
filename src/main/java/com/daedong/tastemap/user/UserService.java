package com.daedong.tastemap.user;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.RsvDTO;
import com.daedong.tastemap.common.EmailService;
import com.daedong.tastemap.common.MySecurityUtils;
import com.daedong.tastemap.security.IAuthenticationFacade;
import com.daedong.tastemap.security.UserDetailsServiceImpl;
import com.daedong.tastemap.user.model.UserEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class UserService{
    @Autowired
    private EmailService email;

    @Autowired
    private MySecurityUtils secUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @Autowired
    private IAuthenticationFacade auth;

    public int join(UserEntity param){
        String authCd = secUtils.getRandomDigit(5);
        //비밀번호 암호화
        String hashedPw = passwordEncoder.encode(param.getPw());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        param.setProvider("local");
        int result = userDetailService.join(param);

        if(result == 1){ //메일쏘기(id, authcd값을 메일로 쏜다)
            String subject = "인증메일입니다";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>"
                    , param.getEmail(), authCd);
            email.sendMimeMessage(param.getEmail(), subject, txt);
        }
        return result;
    }

    //이메일 인증처리
    public int auth(UserEntity param){
        return mapper.auth(param);
    }

    public int idChk(UserEntity param){
        UserEntity param2 = mapper.idChk(param);
        if(param2 == null){
            return 0;
        }
        return 1;

    }

    public String uploadProfile(MultipartFile img){
        UserEntity loginUser = auth.getLoginUser();
        final String PATH = "C:/springImg/" + loginUser.getIuser();

        File folder = new File(PATH);
        folder.mkdirs();

        String ext = FilenameUtils.getExtension(img.getOriginalFilename());
        String fileNm = UUID.randomUUID().toString() + "." + ext;

        File target = new File(PATH + "/" + fileNm);
        try {
            img.transferTo(target);

            //이전 이미지 삭제
            File delFile = new File(PATH + "/" + loginUser.getProfileImg());
            if(delFile.exists()){
                delFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserEntity param = new UserEntity();
        param.setIuser(loginUser.getIuser());
        param.setProfileImg(fileNm);

        mapper.updUser(param);

        loginUser.setProfileImg(fileNm);

        return "/user/mypage";
    }

    public List<RsvDTO> selRsvList(UserEntity param){return mapper.selRsvList(param);}
    public List<BoardDomain> selFavList(UserEntity param){return mapper.selFavList(param);}
}

