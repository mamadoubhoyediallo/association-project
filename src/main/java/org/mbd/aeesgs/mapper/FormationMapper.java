//package org.mbd.aeesgs.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import org.mbd.aeesgs.dto.FormationDto;
//import org.mbd.aeesgs.model.Formation;
//import org.springframework.stereotype.Component;
//
//@Mapper(componentModel = "spring")
//@Component
//public interface FormationMapper {
//    FormationMapper INSTANCE = Mappers.getMapper(FormationMapper.class);
//    Formation toFormation(FormationDto formationDto);
//    @Mapping(source = "organisateur.id", target = "id_organisateur")
//    FormationDto toFormationDto(Formation formation);
//}
