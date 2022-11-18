package com.example.crudproject;

import com.example.bean.MemberVO;
import com.example.dao.MemberDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUpload {
    public MemberVO uploadPhoto(HttpServletRequest request) {
        String filename = ""; // 업로드되는 파일이름 저장용
        int sizeLimit = 15 * 1024 * 1024; // 파일크기 (15MB)

        // 실제로 서버에 저장되는 path를 upload로 정한다.
        String realPath = request.getServletContext().getRealPath(("upload"));
        //System.out.println(realPath);

        // 혹시 저장될 경로가 없으 생성한다.
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        MemberVO one = null;
        MultipartRequest multipartRequest = null;
        try {
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

            filename = multipartRequest.getFilesystemName("photo");

            one = new MemberVO();
            String sid = multipartRequest.getParameter("sid");
            if(sid!=null&&!sid.equals("")) one.setSid(Integer.parseInt(sid));
            one.setUserid(multipartRequest.getParameter("userid"));
            one.setUsername(multipartRequest.getParameter("username"));
            one.setPassword(multipartRequest.getParameter("password"));
            one.setEmail(multipartRequest.getParameter("email"));
            one.setBlogurl(multipartRequest.getParameter("blogurl"));
            one.setDetail(multipartRequest.getParameter("detail"));
            if(sid!=null && !sid.equals("")) {
                MemberDAO dao = new MemberDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(sid));
                if(filename != null && oldfilename != null) {
                    FileUpload.deleteFile(request, oldfilename);
                }
                else if(filename == null && oldfilename != null) {
                    filename = oldfilename;
                }
            }
            one.setPhoto(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String oldfilename) {
        String filePath = request.getServletContext().getRealPath("upload");

        File f = new File(filePath + "/" + oldfilename);
        if(f.exists()) f.delete();
    }
}
