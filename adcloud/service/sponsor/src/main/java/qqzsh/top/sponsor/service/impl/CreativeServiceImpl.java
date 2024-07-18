package qqzsh.top.sponsor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.sponsor.dao.CreativeRepository;
import qqzsh.top.sponsor.entity.Creative;
import qqzsh.top.sponsor.service.ICreativeService;
import qqzsh.top.sponsor.vo.CreativeRequest;
import qqzsh.top.sponsor.vo.CreativeResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:00
 * @Description
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
