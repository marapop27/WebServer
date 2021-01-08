#{{{ Marathon
from default import *
#}}} Marathon

def test():    set_java_recorded_version("1.8.0_265")
    if window(''):
        click('Stop Server')
        click('Maintenance')
        click('Start Server')
        click('Maintenance')
        click('Stop Server')
        click('Maintenance')
        click('Start Server')
        click('Maintenance')
        click('Stop Server')
        click('Maintenance')
        click('Start Server')
        assert_p('lbl:DESKTOP-UMVB6IM/192.168.100.8', 'Text', 'DESKTOP-UMVB6IM/192.168.100.8')
        assert_p('lbl:RUNNING', 'Text', 'RUNNING')
	i = 1
        while i < 100:
		click('Start Server')
		click('Stop Server')
		i += 1
        click('Start Server')
        click('Maintenance')
        click('Stop Server')
        click('Start Server')
        click('Stop Server')
        select('JTextField_0', 'C:\\Users\\mara\\Desktop\\TestSitee')
        click('Maintenance')
        click('Start Server')
        click('JTextField_0')
        rightclick('JTextField_0')
        assert_p('JTextField_0', 'Text', 'C:\\Users\\mara\\Desktop\\TestSitee')
        click('Stop Server')
        click('Maintenance')
        click('Start Server')
        click('Stop Server')
        select('JTextField_1', '8088')
        click('Start Server')
        rightclick('lbl:STOPPED_2')
        assert_p('lbl:STOPPED_2', 'Text', '8088')
    close()


    pass