package sio.springbootaventureproject.controller.model.dao

import org.springframework.data.jpa.repository.JpaRepository
import sio.springbootaventureproject.controller.model.entity.Arme

interface ArmeDao : JpaRepository<Arme, Long> {
}