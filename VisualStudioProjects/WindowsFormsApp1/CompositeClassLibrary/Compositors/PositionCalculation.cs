using IntervalDisplayLibrary.Components;
using IntervalDisplayLibrary.Components.Interface;
using Model.ValueTypes;

namespace IntervalDisplayLibrary.Compositors
{
    internal class PositionCalculation
    {
        private IIntervalComponent _componentMinDateTime; // TODO: change type to DateTime
        private IIntervalComponent _componentMaxDateTime; // TODO: change type to DateTime
        private Composite _composite;
        private long _offset;
        private long _res;
        private const int LineHeight = 50;

        internal PositionCalculation(Composite composite)
        {
            _composite = composite;
        }

        internal void SetPositions(int width)
        {
            SetMinMaxDateTime(_composite);

            _offset = _componentMinDateTime.Interval.StartDateTime.Ticks;
            _res = ResolutionUnit(width);

            SetPosition(_composite);
        }

        private long _y = 0;
        private void SetPosition(IComponent componentToPosition)
        {
            Composite compositeToPosition;
            compositeToPosition = componentToPosition as Composite;

            if (compositeToPosition != null)
            {
                foreach (IComponent component in compositeToPosition)
                {
                    if (component is IComposite)
                    {
                        SetPosition(component);
                    }

                    IIntervalComponent ic = component as IIntervalComponent;
                    if (ic != null && ic.Interval != null)
                    {
                        IntervalType i = ic.Interval;
                        i.XStart = (i.StartDateTime.Ticks - _offset) / _res;
                        i.XEnd = (i.EndDateTime.Value.Ticks - _offset) / _res; // TODO: null check
                        i.Y = _y += LineHeight;
                    }

                    IComposite composite = component as IComposite;
                    if(composite != null)
                    {
                        composite.YCoord = _y += LineHeight;
                    }
                }
            }
        }

        private long ResolutionUnit(int width)
        {
            var minTicks = _componentMinDateTime.Interval.StartDateTime.Ticks;
            var maxTicks = _componentMaxDateTime.Interval.EndDateTime.Value.Ticks; // TODO: null value??
            var ticksPerPixel = (maxTicks - minTicks) / width;
            return ticksPerPixel;
        }

        private void SetMinMaxDateTime(IComponent component)
        {
            Composite composite;
            composite = component as Composite;

            if (composite != null)
            {
                foreach (IComponent c in composite)
                {
                    if (c is IComposite)
                    {
                        SetMinMaxDateTime(c);
                    }

                    IIntervalComponent ic = c as IIntervalComponent; // TODO: Consider moving some calculation to composite....
                    if (ic != null && ic.Interval != null)
                    {
                        if (_componentMinDateTime == null)
                        {
                            _componentMinDateTime = ic;
                        }
                        else if (ic.Interval.StartDateTime < _componentMinDateTime.Interval.StartDateTime)
                        {
                            _componentMinDateTime = ic;
                        }
                        if (_componentMaxDateTime == null)
                        {
                            _componentMaxDateTime = ic;
                        }
                        else if (ic.Interval.EndDateTime > _componentMaxDateTime.Interval.EndDateTime)
                        {
                            _componentMaxDateTime = ic;
                        }
                    }
                }
            }
        }
    }
}