package com.newcrud

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PlayerServiceSpec extends Specification {

    PlayerService playerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Player(...).save(flush: true, failOnError: true)
        //new Player(...).save(flush: true, failOnError: true)
        //Player player = new Player(...).save(flush: true, failOnError: true)
        //new Player(...).save(flush: true, failOnError: true)
        //new Player(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //player.id
    }

    void "test get"() {
        setupData()

        expect:
        playerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Player> playerList = playerService.list(max: 2, offset: 2)

        then:
        playerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        playerService.count() == 5
    }

    void "test delete"() {
        Long playerId = setupData()

        expect:
        playerService.count() == 5

        when:
        playerService.delete(playerId)
        sessionFactory.currentSession.flush()

        then:
        playerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Player player = new Player()
        playerService.save(player)

        then:
        player.id != null
    }
}
