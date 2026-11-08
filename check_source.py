import re

with open('Android/src/app/src/main/res/values/strings.xml', encoding='utf-8') as f:
    content = f.read()

for name in ['live_metrics_info_tooltip', 'download_extra_data_notification_content_fail', 'download_extra_data_notification_content_paused', 'download_extra_data_notification_content_success']:
    m = re.search(r'<string name="' + name + r'"[^>]*>(.*?)</string>', content, re.DOTALL)
    if m:
        print(f'{name}: {repr(m.group(1)[:300])}')
    else:
        print(f'{name}: NOT FOUND')
